<?php
require_once("SiteRestHandler.php");

$view = "";
if(isset($_GET["view"]))
    $view = $_GET["view"];
/*
 * RESTful service ???
 * URL ??
*/
switch($view){

    case "all":
        // ?? REST Url /site/list/
        $siteRestHandler = new SiteRestHandler();
        $siteRestHandler->getAllSites();
	break;

    case "single":
        // ?? REST Url /site/show/<id>/
        $siteRestHandler = new SiteRestHandler();
        $siteRestHandler->getSite($_GET["id"]);
        break;

    case "" :
        //404 - not found;
        break;
}
?>
