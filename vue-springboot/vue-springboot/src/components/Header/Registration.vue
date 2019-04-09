<template>
    <div class="submitform">
        <div v-if="!submitted">
            <div v-if="samePassword">
                <h4>Your password doesn't match</h4>
            </div>
            <div v-if="userAlreadyExist">
                <h4>This username is already used</h4>
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" required v-model="user.username" name="username">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" required v-model="user.password" name="password">
            </div>

            <div class="form-group">
                <label for="password">Repeat password</label>
                <input type="password" class="form-control" id="repeatPassword" required v-model="user.repeatPassword" name="password">
            </div>
            <button v-on:click="register()" class="btn btn-success">Registration</button>
        </div>


        <!--<router-link class="btn btn-success" to="{ name: 'menubar', query: { login: true }}">Connect</router-link>-->
    </div>
</template>

<script>
    import router from '../../router';
    import http from '../../http-common';

    export default {
        name: "registration",
        data(){
            return{
                user: {
                    id: 0,
                    username: "",
                    password: "",
                    repeatPassword: ""
                },
                users: [],
                submitted: false,
                samePassword: false,
                userAlreadyExist: false
            }
        },
        methods:{
            checkIfUserExist() {
                var data = {
                    username: this.user.username,
                    password: this.user.password,
                    repeatPassword: this.user.repeatPassword
                };
                console.log("Check if user exists");
                http.get("/user").then(response => {
                    this.users = response.data;
                    console.log(this.users);
                    var i;
                    for(i = 0; i < this.users.length; i++){
                        console.log(this.users[i].username);
                        if (this.users[i].username === data.username){
                            this.userAlreadyExist = true;
                        }
                    }
                    console.log("userAlreadyexist =" + this.userAlreadyExist);
                    if(!this.userAlreadyExist){
                        this.registerUser();
                    }
                });
            },
            registerUser() {
                var data = {
                    username: this.user.username,
                    password: this.user.password,
                    repeatPassword: this.user.repeatPassword
                };
                console.log("Register user");
                if(!this.userAlreadyExist){
                    if (data.password === data.repeatPassword){

                        http.post("/user", data).then(response => {
                            this.user.id = response.data.id;
                            console.log(response.data);
                        }).catch(e => {
                            console.log(e)
                        });
                        var loggedin = true;
                        this.$emit('clicked', loggedin);
                        console.log(loggedin);
                        router.push({ name: "menubar"
                            // , query: { loggedin: true }
                        });
                        this.submitted = true;
                    }else {
                        this.samePassword = true;
                    }
                }
            },

            register() {
                this.checkIfUserExist();
            }
        }
    }
</script>

<style>
    .submitform {
        max-width: 300px;
        margin: auto;
    }
</style>