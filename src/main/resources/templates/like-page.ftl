<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Tinder | Likes</title>

    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/styles.css">
    <link rel="stylesheet" href="/assets/css/cover.css">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .b-example-divider {
            height: 3rem;
            background-color: rgba(0, 0, 0, .1);
            border: solid rgba(0, 0, 0, .15);
            border-width: 1px 0;
            box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
        }

        .b-example-vr {
            flex-shrink: 0;
            width: 1.5rem;
            height: 100vh;
        }

        .bi {
            vertical-align: -.125em;
            fill: currentColor;
        }

        .nav-scroller {
            position: relative;
            z-index: 2;
            height: 2.75rem;
            overflow-y: hidden;
        }

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
                <a href="/profiles" role="button" class="nav-link py-1 px-0 active">Meet new people</a>
                <a class="nav-link py-1 px-0" href="">Safety</a>
                <a class="nav-link  py-1 px-0" href="">Support</a>
                <a class="nav-link py-1 px-0" href="">Features</a>
                <a href="/logout" role="button" class="nav-link py-1 px-0 active">log out</a>
            </nav>
        </div>
    </header>

    <main class="px-3">
        <#if likedUsers?size != 0 >
            <h1 class="h3 mb-3 font-weight-normal" style="color: #428dff;">Here is your Tinders</h1>

            <table class="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">Avatar</th>
                        <th scope="col">Name</th>
                        <th scope="col">Last Login</th>
                        <th scope="col">About</th>
                        <th scope="col">Age</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <#list likedUsers as user>
                        <#if user??>
                            <tr>
                                <td>
                                    <#if user.pictureSrc??>
                                        <div class="avatar-img">
                                            <img class="img-circle" alt="avatar" src="${user.pictureSrc}" style="max-width: 100%; height: auto;"/>
                                        </div>
                                    <#else>
                                        <svg class="bd-placeholder-img img-fluid rounded-end" width="100" height="100"
                                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder:Image" preserveAspectRatio="xMidYMid slice" focusable="false">
                                            <title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"></rect>
                                            <text x="50%" y="50%" fill="#dee2e6" dy=".3em">${user.userName} photo coming soon :)</text>
                                        </svg>
                                    </#if>
                                </td>
                                <td>${user.userName}</td>
                                <td>${user.onlineStatus}</td>
                                <td>${user.aboutSelf}</td>
                                <td>${user.age}</td>
                                <td>
                                    <form action=`/messages/${user.userId}` method="get">
                                        <button class="btn btn-primary" type="submit">Start chatting</button>
                                    </form>
                                </td>
                            </tr>
                        </#if>
                    </#list>
                </tbody>
            </table>
        <#else>
            <h1 class="h3 mb-3 font-weight-normal" style="color: #428dff;">You have not liked users</h1>
        </#if>
    </main>

    <footer class="mt-auto text-white-50">
        <p class="text-white-70">&copy; Tinder 2018</p>
    </footer>

</div>
</body>
</html>