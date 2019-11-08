
<%--
  Created by IntelliJ IDEA.
  User: CiteflaZe
  Date: 08-Nov-19
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<html lang="param.locale">
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <script src="script.js" type="text/javascript"></script>
        <title>Login</title>

        <style>
            body{
                padding-top:4.2rem;
                padding-bottom:4.2rem;
                background:rgba(0, 0, 0, 0.76);
            }
            a{
                text-decoration:none !important;
            }
            h1,h2,h3{
                font-family: 'Kaushan Script', cursive;
            }
            .myform{
                position: relative;
                display: -ms-flexbox;
                display: flex;
                padding: 1rem;
                -ms-flex-direction: column;
                flex-direction: column;
                width: 100%;
                pointer-events: auto;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid rgba(0,0,0,.2);
                border-radius: 1.1rem;
                outline: 0;
                max-width: 500px;
            }
            .tx-tfm{
                text-transform:uppercase;
            }
            .mybtn{
                border-radius:50px;
            }
        </style>
    </head>

    <body>
    <div class="container">
        <div class="row">
            <div class="col-md-5 mx-auto">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1>Login</h1>
                        </div>
                    </div>
                    <form action="/user" method="post" name="login">
                        <input type="hidden" name="command" value="login">
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" name="email" id="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password"  class="form-control" aria-describedby="emailHelp" placeholder="Enter Password">
                            <div class="form-group">
                                <p class="text-center">Please fill in all fields</p>
                            </div>
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
                        </div>

                        <div class="form-group">
                            <p class="text-center">Don't have account? <a href="register.jsp" id="signup">Sign up here</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    </body>

</html>




