import os

# Lista de objetos
objetos = ["EmgPersonal", "EmgService", "EmgVehicle", "Incident", "Intervention", "Report"]

# Lista de operaciones
operaciones = ["GET", "POST", "PUT", "DELETE"]

# Ruta de la carpeta de destino
ruta_carpeta = "ArchivosJSON"


if not os.path.exists(ruta_carpeta):
    os.makedirs(ruta_carpeta)

for objeto in objetos:
    for operacion in operaciones:
        for i in range(1, 4):
            if i < 3:
                nombre_archivo = f"{operacion}{objeto}-ok{i}.json"
            else:
                nombre_archivo = f"{operacion}{objeto}-ko.json"
                
            ruta_archivo = os.path.join(ruta_carpeta, nombre_archivo)

            with open(ruta_archivo, "w") as archivo:
                archivo.write("{}")

            print(f"Archivo creado: {ruta_archivo}")
