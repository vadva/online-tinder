<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Tinder | Meet New People Profiles</title>

    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/styles.css">
    <link href="/assets/css/cover.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

    <style>
        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }
    </style>
</head>
<body class="d-flex h-100 text-center text-white bg-dark">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

        <header class="mb-auto">
            <div>
                <a href="/tinder" role="button">
                    <img src="/assets/img/tinder_logo_blue.png" alt="Tinder | Dating, Make Friends & Meet New People | Welcome"
                         width="18%" height="18%" class="float-md-start d-inline-block align-text-top"></a>
                <nav class="nav nav-masthead justify-content-center float-md-end">
                    <a href="/liked" role="button" class="nav-link py-1 px-0 active">Check your TinderS</a>
                    <a href="/logout" role="button" class="nav-link py-1 px-0 active">log out</a>
                </nav>
            </div>
        </header>

        <main class="px-3">
            <div class="container">
                <div class="row">
                    <div class="chat-main col-6 offset-3">
                        <div class="col-md-12 chat-header">
                            <div class="row header-one text-white p-1">
                                <div class="col-md-6 name pl-2">
                                    <i class="fa fa-comment"></i>
                                    <h6 class="ml-1 mb-0">${userWhoWrite.getUserName()}</h6>
                                </div>
                                <div class="col-md-6 options text-right pr-0">
                                    <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                                    <p class="arrow-up mb-0">
                                        <i class="fa fa-arrow-up text-center pt-1"></i>
                                    </p>
                                    <i class="fa fa-times hover text-center pt-1"></i>
                                </div>
                            </div>
                        </div>
                        <div class="chat-content">
                            <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">

                                <#list messages as message>
                                    <#if message.getUser_id_who_write()=userWhoWrite.getUserId()>

                                        <ul class="p-0 d-flex justify-content-end">
                                            <li class="send-msg float-right mb-2">
                                                <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">
                                                    ${message.getMessage_text()}<br>
                                                </p>
                                                <span class="receive-msg-time">${userWhoWrite.getUserName()}, Jan 25, 6:20 PM</span>
                                            </li>
                                        </ul>

                                    <#else>
                                        <ul class="p-0 ">
                                            <li class="receive-msg float-left mb-2">
                                                <div class="sender-img">
                                                    <img src="http://nicesnippets.com/demo/image1.jpg" class="float-left">
                                                </div>

                                                <div class="receive-msg-desc float-left ml-2">
                                                    <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                                        ${message.getMessage_text()}<br>
                                                    </p>
                                                    <span class="receive-msg-time">ketty, Jan 25, 6:20 PM</span>
                                                </div>
                                            </li>
                                        </ul>
                                    </#if>
                                </#list>
                            </div>

                            <div class="col-md-12 p-2 msg-box border border-primary">
                                <div class="row">
                                    <div class="col-md-2 options-left">
                                        <i class="fa fa-smile-o"></i>
                                    </div>
                                    <div class="pl-0">
                                        <form class="d-flex"  method="get">
                                            <input type="text" name="text" class="border-0 col" placeholder=" text message"/>
                                            <button class="btn btn-primary" type="submit">Send</button>
                                        </form>

                                    </div>
                                    <div class="col-md-3 text-right options-right">
                                        <i class="fa fa-picture-o mr-2"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <footer class="mt-auto text-white-50">
            <p class="text-white-70">&copy; Tinder 2022</p>
        </footer>
    </div>
    <script src="/assets/js/bootstrap.bundle.min.js"></script>

</body>
</html>