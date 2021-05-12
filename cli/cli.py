import socket
import argparse


DELIMITER = b'\r\n'
CHUNK_SIZE = 8192


def get_response(client_socket):
    response = b''
    while True:
        chunk = client_socket.recv(CHUNK_SIZE)
        try:
            delimiter_index = chunk.index(DELIMITER)
            if delimiter_index != len(chunk) - 2:
                print("Invalid response format!")
                exit(1)
            response += chunk
            break
        except ValueError:
            response += chunk
    return response.decode('utf-8').strip()


def encode_command(raw_command):
    encoded_command = raw_command.strip().encode('utf-8') + DELIMITER
    return encoded_command


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Pastore cli")
    parser.add_argument('-host', type=str, help='pastore host', default='127.0.0.1')
    parser.add_argument('-port', type=int, help='pastore port', default='8967')
    args = parser.parse_args()
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((args.host, args.port))
        while True:
            try:
                command = input('Pastore > ')
                encoded_command = encode_command(command)
                client_socket.sendall(encoded_command)
                if command.strip() == 'EXIT':
                    exit(0)
                response = get_response(client_socket)
                print(response)
            except BrokenPipeError:
                print("server closed connection!")
                break
