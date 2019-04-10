<template>
    <div class="submitform">
        <div v-if="!submitted">
            <br>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" required v-model="user.username" name="username">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" required v-model="user.password" name="password">
            </div>

            <!--<router-link v-on:click="checkCredentials()" class="btn btn-success" to="/">Connect routerlink</router-link>-->
            <button v-on:click="checkCredentials()" class="btn btn-success">Connect button</button>
            <br>
            <div v-if="errorCredentials">
                <h4>Your username and your password don't match</h4>
            </div>
        </div>
    </div>
</template>

<script>
    import router from '../../router';
    import http from '../../http-common';

    export default {
        name: "login",
        data(){
            return{
                user: {
                    id: 0,
                    username: "",
                    password: ""
                },
                submitted: false,
                validCredentials: false,
                errorCredentials: false
            }
        },
        methods:{
            checkCredentials() {
                var data = {
                    username: this.user.username,
                    password: this.user.password,
                    repeatPassword: this.user.repeatPassword
                };
                console.log("Check credentials");
                http.get("/user").then(response => {
                    this.users = response.data;
                    console.log(this.users);
                    var i;
                    for(i = 0; i < this.users.length; i++){
                        console.log(this.users[i].username);
                        if (this.users[i].username === data.username
                        && this.users[i].password === data.password){
                            this.validCredentials = true;
                        }
                    }
                    console.log("userAlreadyExist =" + this.validCredentials);
                    if(this.validCredentials)
                        this.logUser();
                    else
                        this.errorCredentials = true;
                });
            },
            logUser() {
                var loggedin = true;
                this.$emit('isUserActive', loggedin);
                this.$emit('userSession', this.user.username);
                this.$router.push({ name: 'project-list' });
                this.submitted = true;
            },
        }
    }
</script>

<style>
    .submitform {
        max-width: 300px;
        margin: auto;
    }
</style>