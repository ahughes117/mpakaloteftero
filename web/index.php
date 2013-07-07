<?php
require_once('config.php');
$config = new Config();
?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css"/>
        <title><?php echo "{$config->site_title}"; ?></title>
    </head>
    <body class="fd_content">
        <p><img src="<?php echo $config->header_img ?>" class="fd_img_center" width='1000' height='127'/></p>

        <form name="fr_login" id="fr_login" action="login.php" method="post" onsubmit="return submitCheck(this, this.id);">
            <fieldset class="nmFdetails">

                <p class="nmlabel">Email: <br>
                    <input class="nonz vemail text-input" id="email" name="email" autocomplete="on" type="text" tabindex="1" /></p>

                <p class="nmlabel">Password: <br>
                    <input class="nonz text-input" id="password" name="password" type="password" tabindex="2"/></p>

                <p><a href="mailto:<?php echo $config->site_admin ?>" id="show">Forgot your password?</a></p>
            </fieldset>
            <fieldset class="nmFbutton">
                <input class="blue_button_s" type="submit" value="LOGIN" />
            </fieldset>
        </form>
        <?php
        if (isset($_GET['error']))
            echo "<p>wrong username/password combination</p>";
        ?>
        <p>
            This page is proudly powered by <a href="http://www.github.com/ahughes117/mpakaloteftero" target="_blank">mpakaloteftero</a>
        </p>
    </body>
</html>
