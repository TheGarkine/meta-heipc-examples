#include <iostream>
#include <memory>
#include <string>

#include <grpcpp/grpcpp.h>
#include "greeter.grpc.pb.h"  // Generated gRPC header

using grpc::Channel;
using grpc::ClientContext;
using grpc::Status;
using grpcservice::Greeter;
using grpcservice::HelloReply;
using grpcservice::HelloRequest;

class GreeterClient {
public:
    // Constructor initializes the client with a gRPC channel to the server
    GreeterClient(std::shared_ptr<Channel> channel)
        : stub_(Greeter::NewStub(channel)) {}

    // Call SayHello method on the server and get the response
    std::string SayHello(const std::string& user) {
        // Create a request and set the name field
        HelloRequest request;
        request.set_name(user);

        // Create a reply object to hold the server's response
        HelloReply reply;

        // Client context holds metadata and settings for the RPC call
        ClientContext context;

        // Perform the actual RPC call
        Status status = stub_->SayHello(&context, request, &reply);

        // Check if the RPC call was successful
        if (status.ok()) {
            return reply.message();  // Return the message field from the reply
        } else {
            std::cerr << "RPC failed: " << status.error_message() << std::endl;
            return "RPC failed";
        }
    }

private:
    // The stub for interacting with the Greeter service
    std::unique_ptr<Greeter::Stub> stub_;
};

int main(int argc, char** argv) {
    // Check if the user passed a name argument, otherwise use a default
    std::string user = (argc > 1) ? argv[1] : "World";

    // Create a channel to the server at the specified address and port
    GreeterClient greeter(grpc::CreateChannel("localhost:50051", grpc::InsecureChannelCredentials()));

    // Call SayHello and get the server's response
    std::string reply = greeter.SayHello(user);
    
    // Print the server's response
    std::cout << "Greeter received: " << reply << std::endl;

    return 0;
}