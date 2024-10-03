SUMMARY = "Minimal gRPC C++ project"
LICENSE = "CLOSED"

SRC_URI = "file://CMakeLists.txt \
           file://src/greeter.proto \
           file://src/grpc_server.service \
           file://src/main.cpp"

DEPENDS = "grpc protobuf abseil-cpp protobuf-native grpc-native"

inherit cmake systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "grpc_server.service"

HEIPC_PROTO_LOCATION = "${S}/src/greeter.proto"

S = "${WORKDIR}"

# NOTE: You can also do this directly in CMake, which may create a better development cycle!
do_configure:prepend() {
    # Generate Protobuf source files
    ${STAGING_BINDIR_NATIVE}/protoc --proto_path=${S}/src \
        --cpp_out=${B} ${S}/src/greeter.proto
    
    # Generate gRPC source files
    ${STAGING_BINDIR_NATIVE}/protoc --proto_path=${S}/src \
        --grpc_out=${B} --plugin=protoc-gen-grpc=${STAGING_BINDIR_NATIVE}/grpc_cpp_plugin ${S}/src/greeter.proto
}

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/src/grpc_server.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "${bindir}/grpc_server"

inherit heipc