#!/usr/bin/env bash
# Waits until the emulator's system services are up and have stayed up for a while.
# sys.boot_completed alone isn't enough on API 36+: system_server can still restart after it.
#
# Usage: wait-for-emulator.sh [timeout-seconds] [settle-seconds]
set -u

timeout_s=${1:-300}
settle_s=${2:-30}
deadline=$((SECONDS + timeout_s))

# Every adb call gets its own timeout, a hung guest otherwise blocks forever
shell() { timeout 10 adb shell "$@" 2>/dev/null | tr -d '\r'; }

ready() {
  [ "$(shell getprop sys.boot_completed)" = "1" ] || return 1
  for service in activity package input window; do
    shell service check "$service" | grep -q ": found" || return 1
  done
  shell pm path android | grep -q "^package:"
}

while [ "$SECONDS" -lt "$deadline" ]; do
  if ready; then
    pid=$(shell pidof system_server)
    echo "System services up (system_server pid $pid), checking they stay up for ${settle_s}s"
    sleep "$settle_s"
    if ready && [ "$(shell pidof system_server)" = "$pid" ]; then
      echo "Emulator ready after ${SECONDS}s"
      exit 0
    fi
    echo "system_server restarted while settling, waiting again"
  fi
  sleep 2
done

echo "Emulator not ready within ${timeout_s}s" >&2
exit 1
