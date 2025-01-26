# Inventor App Backend

This repository provides a backend for managing Autodesk Inventor assemblies and parts programmatically through a RESTful API. The APIs allow operations such as opening and closing assemblies, changing parameters, and suppressing components.

## Prerequisites
- Ensure that Autodesk Inventor is installed on your system.
- The backend must be running on `http://localhost:8080` or your specified host and port.

## API Endpoints

### 1. Open Assembly
**Endpoint:** `/api/open-assembly`

**Method:** `POST`

**Description:** Opens an assembly file in Autodesk Inventor.

**Request Body:**
```json
{
  "assemblyPath": "<path_to_assembly_file>"
}
```

**Example:**
```bash
curl --request POST \
  --url http://localhost:8080/api/open-assembly \
  --header 'Content-Type: application/json' \
  --data '{
	"assemblyPath": "D:\\Project_task\\Projects\\3D Modelling\\66kV\\MODEL\\PC0300949_03_01.iam"
}'
```

### 2. Close Assembly
**Endpoint:** `/api/close-assembly`

**Method:** `POST`

**Description:** Closes the currently open assembly in Autodesk Inventor.

**Example:**
```bash
curl --request POST \
  --url http://localhost:8080/api/close-assembly \
  --header 'User-Agent: insomnia/10.3.0'
```

### 3. Assembly Status
**Endpoint:** `/api/assembly-status`

**Method:** `GET`

**Description:** Retrieves the status of the currently open assembly, including file path and other details.

**Example:**
```bash
curl --request GET \
  --url http://localhost:8080/api/assembly-status \
  --header 'User-Agent: insomnia/10.3.0'
```

### 4. Change Parameters
**Endpoint:** `/api/change-parameters`

**Method:** `POST`

**Description:** Updates the parameters of a specified part file.

**Request Body:**
```json
{
  "partFilePath": "<path_to_part_file>",
  "parameters": [
    {
      "parameterName": "<parameter_name>",
      "newValue": <new_value>
    },
    {
      "parameterName": "<parameter_name>",
      "newValue": <new_value>
    }
  ]
}
```

**Example:**
```bash
curl --request POST \
  --url http://localhost:8080/api/change-parameters \
  --header 'Content-Type: application/json' \
  --data '{
	"partFilePath": "D:/Project_task/Projects/3D Modelling/66kV/MODEL/PC0300949_03_01.ipt",
	"parameters": [
		{
			"parameterName": "d13",
			"newValue": 31
		},
		{
			"parameterName": "d96",
			"newValue": 43
		},
		{
			"parameterName": "d114",
			"newValue": 1014
		}
	]
}'
```

### 5. Suppress Component
**Endpoint:** `/api/suppress-component`

**Method:** `POST`

**Description:** Suppresses or unsuppresses a component in an assembly.

**Request Body:**
```json
{
  "assemblyFilePath": "<path_to_assembly_file>",
  "componentName": "<component_name>",
  "suppress": <true_or_false>
}
```

**Example:**
```bash
curl --request POST \
  --url http://localhost:8080/api/suppress-component \
  --header 'Content-Type: application/json' \
  --data '{
	"assemblyFilePath": "D:/Project_task/Projects/3D Modelling/66kV/MODEL/PC0300949_03_01.iam",
	"componentName": "PC0300949_03_01-04:1",
	"suppress": true
}'
```

## Error Handling
- Ensure that the paths provided in the request body are valid and accessible by the backend.
- Autodesk Inventor must be running for the operations to succeed.
- Errors will be returned as HTTP status codes with error messages.

## Development
- Clone the repository and set up the backend server.
- Ensure all dependencies (e.g., JACOB library) are correctly installed and configured.
- Run the backend server and use the above API endpoints to interact with Inventor.

## License
This project is licensed under the [MIT License](LICENSE).

