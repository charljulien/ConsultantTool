<template>
    <div class="container">
        <div class="starter-template">
            <h1>Login Spring Boot Rest VueJS</h1>
            <div v-if="!loggedin">
                <form>
                    <input class="loginInputThree" type="text" placeholder="username" required v-model="user.username" name="name"/><br>
                    <input class="loginInputThree" type="password" placeholder="password" required v-model="user.password" name="password"/><br>
                    <input class="loginInputTwo" type="submit" v-on:click="loginUser" value="login">
                </form>
                <p color="red">${errorMessage}</p>
            </div>
            <div v-else>
                <h2>Welcome ${name}!!</h2>
                <form action="login" method="Get">
                    <input class="loginInputTwo" type="submit" value="logout">
                </form>
            </div>
        </div>
    </div>
</template>

<script>
    import http from "../http-common";
    export default {
        name: "login",
        data(){
            return{
                user: {
                    id: 0,
                    username: "",
                    password: 0
                },
                loggedin: false
            }
        },

        methods: {
            loginUser() {
                var data = {
                    username: this.user.username,
                    password: this.user.password
                };

                http
                    .post("/login", data)
                    .then(response => {
                        this.user.id = response.data.id;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.loggedin = true;
                console.log(this.user)
            }
        }
    }
</script>

<style scoped>

</style>