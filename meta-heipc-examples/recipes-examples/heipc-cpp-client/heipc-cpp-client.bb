LICENSE = "CLOSED"

inherit cmake

DEPENDS = "protobuf grpc openssl heipc-cpp-server-lib-cpp"
RDEPENDS:${PN} += "heipc-cpp-server-lib-cpp"

FILES:${PN} = "${bindir}/heipc_cpp_client"

SRC_URI ="file://main.cpp \
          file://CMakeLists.txt"

S = "${WORKDIR}"

EXTRA_OECMAKE += "-DCMAKE_INSTALL_PREFIX=${prefix}"
EXTRA_OECMAKE += "-DCMAKE_INSTALL_BINDIR=${bindir}"
EXTRA_OECMAKE += "-DCMAKE_LIBRARY_PATH=${STAGING_LIBDIR}"
EXTRA_OECMAKE += "-DCMAKE_INCLUDE_PATH=${STAGING_INCDIR}"