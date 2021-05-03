import socket
import struct
import argparse



if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Pastore cli")
    parser.add_argument('-host', type=str, help='pastore host', default='127.0.0.1')
    parser.add_argument('-port', type=int, help='pastore port', default='8967')
    args = parser.parse_args()
    delimeter = '\r\n'
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((args.host, args.port))
        while True:
            try:
                message = input('Pastore > ')
                message += delimeter
                encoded_message = message.encode()
                client_socket.sendall(encoded_message)
                response = client_socket.recv(4096)
                print(response.decode().strip())
            except BrokenPipeError:
                print("server closed connection!")
                break
