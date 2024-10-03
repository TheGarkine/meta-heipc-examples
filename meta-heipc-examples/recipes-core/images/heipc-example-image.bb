# Simple recipe for MAX Box image
SUMMARY = "HEIPC Example Image"
DESCRIPTION = "An image installing multiple clients and servers to show the functionalities of the HEIPC toolchain."
LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " heipc-cpp-server \
                   heipc-python-client \
                   heipc-cpp-client \
                   show-build-time "

IMAGE_FEATURES += " debug-tweaks ssh-server-openssh"

inherit extrausers
EXTRA_USERS_PARAMS = "useradd -m -s /bin/bash -P 'root' root"