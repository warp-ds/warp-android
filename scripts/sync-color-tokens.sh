#!/bin/bash

# Syncs the generated color token files from https://github.com/warp-ds/tokens
# into the brand modules. warp-ds/tokens fetches the variables from Figma and
# commits Compose + XML output to output/android, so this script only copies.
#
# Usage: scripts/sync-color-tokens.sh [ref]   (ref defaults to main)

# Exit immediately if a command exits with a non-zero status.
set -euo pipefail

REF="${1:-main}"
PROJECT_ROOT=$(git rev-parse --show-toplevel)
WORK_DIR=$(mktemp -d)
trap 'rm -rf "$WORK_DIR"' EXIT

echo "⬇️  Downloading warp-ds/tokens@$REF"
curl -sfL "https://codeload.github.com/warp-ds/tokens/tar.gz/$REF" | tar -xz -C "$WORK_DIR" --strip-components=1
SOURCE="$WORK_DIR/output/android"

# Upstream writes 8-digit RRGGBBAA hex values as Color(0xFFRRGGBBAA), which
# Compose reads as an opaque color. Rewrite them to Color(0xAARRGGBB).
# TODO: Remove once https://github.com/warp-ds/tokens/pull/41 is merged.
fix_alpha() {
  sed -E -i.bak 's/Color\(0xFF([0-9a-fA-F]{6})([0-9a-fA-F]{2})\)/Color(0x\2\1)/' "$1"
  rm "$1.bak"
}

copy_kotlin() {
  local from="$1" to="$2" package="$3"
  sed -E "1s/^package .*/package $package/" "$from" > "$to"
  fix_alpha "$to"
}

# Upstream dark brand files reference DatavizDarkColors but only import DatavizColors.
# TODO: Remove once https://github.com/warp-ds/tokens/pull/41 is merged.
fix_dataviz_import() {
  sed -i.bak 's/^import com\.schibsted\.nmp\.warp\.theme\.dataviz\.DatavizColors$/import com.schibsted.nmp.warp.theme.dataviz.DatavizDarkColors/' "$1"
  rm "$1.bak"
}

# brand:package
BRANDS=(
  "blocket:com.schibsted.nmp.warp.brands.blocket"
  "dba:com.schibsted.nmp.warp.brands.dba"
  "finn:com.schibsted.nmp.warp.brands.finn"
  "neutral:com.vend.warp.brands.neutral"
  "tori:com.schibsted.nmp.warp.brands.tori"
  "vend:com.schibsted.nmp.warp.brands.vend"
)

for entry in "${BRANDS[@]}"; do
  brand="${entry%%:*}"
  package="${entry#*:}"
  name="$(echo "${brand:0:1}" | tr '[:lower:]' '[:upper:]')${brand:1}"
  module="$PROJECT_ROOT/warp-$brand/src/main"
  kotlin_dir="$module/java/${package//.//}"

  echo "🎨 $name"
  copy_kotlin "$SOURCE/$brand/light/${name}Colors.kt" "$kotlin_dir/${name}Colors.kt" "$package"
  copy_kotlin "$SOURCE/$brand/dark/${name}DarkColors.kt" "$kotlin_dir/${name}DarkColors.kt" "$package"
  fix_dataviz_import "$kotlin_dir/${name}DarkColors.kt"
  cp "$SOURCE/$brand/light/colors.xml" "$module/res/values/colors.xml"
  cp "$SOURCE/$brand/dark/colors.xml" "$module/res/values-night/colors.xml"
done

echo "📊 Dataviz"
dataviz_package="com.schibsted.nmp.warp.theme.dataviz"
dataviz_dir="$PROJECT_ROOT/warp/src/main/java/${dataviz_package//.//}"
copy_kotlin "$SOURCE/dataviz/light/DatavizColors.kt" "$dataviz_dir/DatavizColors.kt" "$dataviz_package"
copy_kotlin "$SOURCE/dataviz/dark/DatavizDarkColors.kt" "$dataviz_dir/DatavizDarkColors.kt" "$dataviz_package"
cp "$SOURCE/dataviz/light/colors.xml" "$PROJECT_ROOT/warp/src/main/res/values/colors.xml"
cp "$SOURCE/dataviz/dark/colors.xml" "$PROJECT_ROOT/warp/src/main/res/values-night/colors.xml"

echo "✅ Synced color tokens from warp-ds/tokens@$REF"
