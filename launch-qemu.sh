#!/bin/bash

qemu-system-aarch64 \
    -M virt \
    -cpu cortex-a53 \
    -m 2048 \
    -kernel build/tmp/deploy/images/qemuarm64/Image \
    -drive file=build/tmp/deploy/images/qemuarm64/heipc-example-image-qemuarm64.rootfs.ext4,format=raw,if=virtio \
    -append "root=/dev/vda console=ttyAMA0" \
    -serial mon:stdio \
    -netdev user,id=mynet0,hostfwd=tcp::50051-:50051 \
    -device virtio-net-pci,netdev=mynet0 \
    -nographic