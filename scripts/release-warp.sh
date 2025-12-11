#!/bin/bash

# Exit immediately if a command exits with a non-zero status.
set -e

# === Configuration ===
# List of modules to process.
# Note: The script will add the leading ':' for gradle tasks.
MODULES=(
    "warp"
    "warp-blocket"
    "warp-dba"
    "warp-finn"
    "warp-tori"
    "warp-neutral"
    "warp-vend"
)

# === Script Logic ===

# Find the root of the git repository to locate gradlew
# This makes the script runnable from any subdirectory.
PROJECT_ROOT=$(git rev-parse --show-toplevel)
GRADLEW="$PROJECT_ROOT/gradlew"

if [ ! -f "$GRADLEW" ]; then
  echo "❌ ERROR: Could not find gradlew at $GRADLEW"
  exit 1
fi

cd "$PROJECT_ROOT"

echo "🚀 Using gradlew at: $GRADLEW"
echo "=================================================="

HAS_FAILURES=false

# Loop through each module
for module in "${MODULES[@]}"; do
    echo "Processing module: $module"
    echo "--------------------------------------------------"

    # --- Assemble Task ---
    ASSEMBLE_TASK=":$module:assemble"
    echo "▶️ Running task: $ASSEMBLE_TASK"

    if "$GRADLEW" "$ASSEMBLE_TASK"; then
        echo "✅ SUCCESS: $ASSEMBLE_TASK completed successfully."
    else
        echo "❌ FAILED: $ASSEMBLE_TASK failed."
        HAS_FAILURES=true
    fi
    echo

    # --- Publish Task ---
    PUBLISH_TASK=":$module:artifactoryPublish"
    echo "▶️ Running task: $PUBLISH_TASK"

    if "$GRADLEW" "$PUBLISH_TASK"; then
        echo "✅ SUCCESS: $PUBLISH_TASK completed successfully."
    else
        echo "❌ FAILED: $PUBLISH_TASK failed."
        HAS_FAILURES=true
    fi
    echo "=================================================="
done


# Final summary
echo
if [ "$HAS_FAILURES" = true ]; then
    echo "🔥🔥 Finished with failures. 🔥🔥"
    exit 1
else
    echo "🎉🎉 All tasks finished successfully! 🎉🎉"
fi

