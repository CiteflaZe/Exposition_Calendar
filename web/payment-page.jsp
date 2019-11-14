<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 13-Nov-19
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="css/paymentPage.css" rel="stylesheet">

    <title>Checkout</title>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        Payment Details
                    </h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <div class="form-group">
                            <label for="cardNumber">
                                Card Number</label>
                            <div class="input-group">
                                <input type="hidden" name = "command" value="makePayment">
                                <input type="text" class="form-control" id="cardNumber" placeholder="Valid Card Number"
                                       required autofocus />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-7 col-md-7">
                                <div class="form-group">
                                    <label for="expityMonth">
                                        Expiration Date</label>
                                    <div class="col-xs-6 col-lg-6 pl-ziro">
                                        <input type="text" class="form-control" id="expityMonth" placeholder="MM" required />
                                    </div>
                                    <div class="col-xs-6 col-lg-6 pl-ziro">
                                        <input type="text" class="form-control" id="expityYear" placeholder="YY" required /></div>
                                </div>
                            </div>
                            <div class="col-xs-5 col-md-5 pull-right">
                                <div class="form-group">
                                    <label for="cvCode">
                                        CVC</label>
                                    <input type="password" class="form-control" id="cvCode" placeholder="CVC" required />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <ul class="nav nav-pills nav-stacked">
                                <li class="active"><span class="badge pull-right"><span class="glyphicon glyphicon-usd"></span><c:out value="${exposition.getTicketPrice()*tickets}"/> </span> <h4>Final Payment</h4>
                                </li>
                            </ul>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-success btn-lg btn-block" role="button">Pay</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
