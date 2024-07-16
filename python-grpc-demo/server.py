# server.py

import grpc
from concurrent import futures
import user_pb2
import user_pb2_grpc

class UserServicer(user_pb2_grpc.LoginServiceServicer):
    def login(self, request, context):
        return user_pb2.LoginResponse(
            success = True,
            system = "Python",
            token = request.username + ":" + request.password
            )

def run_server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    user_pb2_grpc.add_LoginServiceServicer_to_server(UserServicer(), server)
    server.add_insecure_port('[::]:9000')
    server.start()
    server.wait_for_termination()

if __name__ == '__main__':
    run_server()