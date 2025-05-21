<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
.logo {
font-size: 36px;
font-weight: bold;
color: #192c78;
}

.logo span {
color: #ff6b00;
}
</style>

<nav class="navbar navbar-expand-lg" style="background-color: #F1F3F6;">
    <div class="container-fluid">
        <div class="logo">
            DIM<span>O</span>
        </div>
        <button class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

               <span class="navbar-text ms-auto">
                   ${user}
                   <a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
               </span>
        </div>
    </div>
</nav>