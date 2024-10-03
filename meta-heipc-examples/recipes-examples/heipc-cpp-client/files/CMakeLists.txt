cmake_minimum_required(VERSION 3.10)
project(MyGrpcApp)

set(CMAKE_CXX_STANDARD 17)

# Find the required packages
find_package(Protobuf REQUIRED)
find_package(gRPC REQUIRED)

# Include directories for generated gRPC and Protobuf headers
include_directories(${CMAKE_SOURCE_DIR}/include)
include_directories(${CMAKE_INCLUDE_PATH}/heipc_cpp_server_grpc_client)

find_library(HEIPC_GRPC_LIB NAMES heipc_cpp_server_grpc_client PATHS ${CMAKE_LIBRARY_PATH})

# Add the executable
add_executable(heipc_cpp_client main.cpp)

# Link the executable with the generated gRPC client library and gRPC/Protobuf libraries
target_link_libraries(heipc_cpp_client
    ${HEIPC_GRPC_LIB}                 # Path to your generated gRPC client library
    grpc++
    grpc
    protobuf
)

# Specify the include directories for Protobuf and gRPC
target_include_directories(heipc_cpp_client PRIVATE ${Protobuf_INCLUDE_DIRS} ${gRPC_INCLUDE_DIRS})

install(TARGETS heipc_cpp_client RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR})