<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="src/css/card.css">
    <link rel="stylesheet" href="src/css/portfolio.css">
    <link rel="stylesheet" href="src/css/nav.css">
    <link rel="stylesheet" href="src/css/sidebar.css">
    <link rel="stylesheet" href="src/css/root.css">
    <link rel="stylesheet" href="src/css/modal.css">
    <link rel="stylesheet" href="src/css/frame-Actions.css">
    <link rel="stylesheet" href="src/css/form.css">
    <link rel="stylesheet" href="src/css/alerta.css">
    <link rel="stylesheet" href="src/css/login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap" rel="stylesheet">
    <title>notas</title>
</head>

<body>
    <header>
        <h1>Nora Sounds</h1>
        <figure class="logo">
            <img src="src/img/logo.png" alt="Logo">
        </figure>
        
    </header>
    <aside class="sidebar">
        <div class="background-sidebar"></div>
        <div class="opciones">
            <form action="" method="get" name="formulario">
                <label for="xxx">nombre:</label>
                <input type="text" id="xxx" name="Propietario">
                <button type="submit">Saludar!!</button>
            </form>
        </div>
    </aside>
    <section class="portfolio" id="Portafolio">
    <?php
	if (isset($_GET['Propietario']) && !empty($_GET['Propietario']) ) {
	    // instancia de la clase SoapClient
		$client = new SoapClient('http://emanoxxx.com:8000/noranotes?wsdl');	
		// definición y paso de parámetros
		$parametros = array("Propietario" => $_GET['Propietario'] );
		$response = $client->ObtenerNotas($parametros);
        $notas=$response->{'Nota'}; 
        foreach ($notas as &$nota) {
            print '<article  class="card" style="width:200px; height:250px; overflow: visible;display: flex;flex-direction: column;align-content: center"><div id ="'.$nota->{'id'}.'" style=" widht:100%;   overflow: visible;display: flex;flex-direction: column;align-content: center;text-align: center;">';
            print "<h1>Nota: </h1>";
            print "<h2>Titulo: " . $nota->{'Titulo'} . "</h2>";
            print "<h3>Fecha: " . $nota->{'Fecha'} . "</h3>";
            print "<p>id: " . $nota->{'id'} . "</p>";
            print "</div></article>";
        }
    	}
        /*<article class="card"><div style="    overflow: auto;display: flex;flex-direction: column;align-content: center;text-align: center;width:100%;"> */
    ?>
    </section>
</body>
</html>
