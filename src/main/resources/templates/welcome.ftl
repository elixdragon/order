<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email For Order Items Of InternCart</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">
<table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
<tr>
    <td align="center" bgcolor="#ffffff" style="padding: 40px 0 30px 0;">
        <img src='https://i.imgur.com/TPxlhfU.png' width="100" height="100" alt="InternCartlogo" style="display: block;" />
    </td>
</tr>
<tr>
    <td bgcolor="#ffffff" style="padding: 40px 30px 40px 30px;">
        <p>Dear ${user},</p>
        <p>We would like to inform you that your order has been placed successfully.
            <b>Product Details</b>
        <#assign counter = 0>
        <#list productlist as x>
        <br/>
        <hr>
            <p><img src="${x.getPimage()}" alt="productItem" width="80" height="80" align="right" hspace="20">
        Brand Name: ${x.getpBrand()}<br>
        Catagory  :${x.getpCategory()}<br/>
        Product  :${x.getpName()}<br/>
        Price  :${x.getpPrice()}<br/>
        Units Purchase: ${x.getpUnit()}<br/></p>
            <#assign counter = counter + x.getpPrice()>
        </#list>
        <br>
        <p align="right" hspace="20"><b>Total Price : ${counter}</b></p>
    </td>
</tr>
<tr>
    <td bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
        <p>${signature}</p>
        <p>${location}</p>
    </td>
</tr>
</table>

</body>
</html>
