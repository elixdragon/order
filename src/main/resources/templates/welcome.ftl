<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>Sending Email For Order Items Of InternCart</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
        <style>

         footer {
             background: #222;
         }
        footer .copyright-section {
            background: rgb(0, 103, 172);
            padding: 30px 0;
        }
        footer .copyright-section .logo {
            max-width: 300px;
            margin: 0 auto;
        }
        footer .copyright-section .social-media {
            margin-top: 30px;
        }
        footer .copyright-section .social-media ul {
            text-align: center;
        }
        footer .copyright-section .social-media ul li a {
            display: block;
            color: #FA5C4A;
            width: 40px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            border: 1px solid #FA5C4A;
        }
        footer .copyright-section .social-media ul li a:hover {
            background: #FA5C4A;
            color: #fff;
        }
        footer .copyright-section .copyright {
            margin-top: 30px;
        }
        footer .copyright-section .copyright p {
            text-align: center;
            color: #fff;
        }
        footer .demo-logo{
            margin: 0;
            padding: 0;
            text-align: center;
            font-size: 36px;
            text-transform: uppercase;
            color: #fff;
        }
        footer .demo-logo strong{
            color: #FA5C4A;
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
            <p><img src="${x.getProductImage()}" alt="productItem" style="width: auto; height: auto; max-width: 100px; max-height:80px" align="right" hspace="20">
        Brand    : ${x.getProductBrand()}<br>
        Category :${x.getProductCategory()}<br/>
        Product  :${x.getProductName()}<br/>
        Price    :${x.getProductPrice()}<br/>
        Units    : ${x.getProductUnit()}<br/></p>
            <#assign counter = counter + x.getProductPrice()>
        </#list>
        <br>
        <p align="right" hspace="20"><b>Total Price : ${counter}</b></p>
    </td>
</tr>
<tr>
    <td bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
        <footer>
            <h2 class="demo-logo">Intern<strong>Cart</strong></h2>
            <p>${location}</p>
        </footer>
    </td>
</tr>
</table>

</body>
</html>
