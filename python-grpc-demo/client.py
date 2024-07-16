# client.py

from flask import Flask, request, jsonify
import grpc
import user_pb2
import user_pb2_grpc

app = Flask(__name__)

@app.route('/user/login', methods=['POST'])
def grpc_login():
    data = request.get_json()
    username = data.get('username')
    password = data.get('password')

    with grpc.insecure_channel('localhost:9000') as channel:
        stub = user_pb2_grpc.LoginServiceStub(channel)
        response = stub.login(user_pb2.LoginRequest(
            username=username,
            password=password
            ))

    # 将gRPC响应转换为JSON格式
    return jsonify({
        'success': response.success,
        'system': response.system,
        'token': response.token
    })

if __name__ == '__main__':
    app.run(port=8081)