<template>
    <div class="submitform">
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
            <input type="password" class="form-control" id="repeatPassword" required v-model="user.password" name="password">
        </div>

        <button v-on:click="registerUser()" class="btn btn-success">Registration</button>
        <!--<router-link class="btn btn-success" to="{ name: 'menubar', query: { login: true }}">Connect</router-link>-->
    </div>
</template>

<script>
    import router from '../../router'

    export default {
        name: "registration",
        data(){
            return{
                user: {
                    id: 0,
                    username: "",
                    password: ""
                }
            }
        },
        methods:{
            registerUser() {
                var loggedin = true;
                this.$emit('clicked', loggedin);
                console.log(loggedin);
                router.push({ name: "menubar"
                    // , query: { loggedin: true }
                });
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