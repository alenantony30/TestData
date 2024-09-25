from flask import Flask, jsonify
import indian_names

app = Flask(__name__)

@app.route('/get-full-name', methods=['GET'])
def get_full_name():
    try:
        first_name = indian_names.get_first_name()  # Call the function
        last_name = indian_names.get_last_name()  # Call the function
        full_name = first_name + " " + last_name  # Ensure proper indentation
        return jsonify({"full_name": full_name, "first_name": first_name, "last_name": last_name}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)  # Run on all available interfaces
