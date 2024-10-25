<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.css">
    <title>Ingreso al Sistema</title>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-primary text-white text-center">
                    <h3>Ingreso al Sistema</h3>
                </div>
                <div class="card-body">
                    <form method="post" action="Logon" onsubmit="return validateForm()">
                        <div class="form-group">
                            <label for="usuario">Usuario:</label>
                            <input type="text" class="form-control" id="usuario" name="usuario" required>
                        </div>
                        <div class="form-group">
                            <label for="clave">Clave:</label>
                            <input type="password" class="form-control" id="clave" name="clave" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Ingresar</button>
                    </form>
                </div>
                <div class="card-footer text-danger text-center">
                    ${mensaje}
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script>
    function validateForm() {
        const usuario = document.getElementById('usuario').value;
        const clave = document.getElementById('clave').value;

        if (usuario && clave) {
            return swal({
                title: 'Éxito',
                text: 'Formulario enviado correctamente.',
                icon: 'success',
                button: 'Aceptar'
            }).then(() => {
                document.forms[0].submit();
            });
        } else {
            swal({
                title: 'Error',
                text: 'Por favor, complete todos los campos.',
                icon: 'error',
                button: 'Aceptar'
            });
            return false;
        }
    }
</script>
</body>
</html>

