<template>
    <div id="app" class="container-fluid">
        <p>login is set to {{ loggedin }}</p>
        <p>Route query login {{ this.$route.query.loggedin }}</p>
        <div v-if="!loggedin">
            <nav>
                <!--<router-link class="btn btn-primary" to="/">Customers</router-link>-->
                <router-link class="btn btn-primary" to= "/menubar/login">Login</router-link>
                <router-link class="btn btn-primary" to="/menubar/register">Register</router-link>
            </nav>
        </div>
        <div v-else>
            <nav>
                <!--<router-link class="btn btn-primary" to="/">Customers</router-link>-->
                <button class="btn btn-primary" v-on:click="logout()">Logout</button>
                <router-link class="btn btn-primary" to="/edit">Edit</router-link>
            </nav>
        </div>
        <!--<app-login @clicked="onClickChild"></app-login>-->
        <router-view @clicked="onClickChild"></router-view>
        <!--<router-view></router-view>-->


        <br/>
    </div>
</template>

<script>
    // import Login from "../Header/Login";

    export default {
        components: {
          // 'app-login': Login
        },
        name: "menubar",
        data(){
            return{
                loggedin:false
            }
        },
        created() {
            if(this.$route.query.loggedin){
                this.loggedin = this.$route.query.loggedin;
            }
        },
        methods: {
            logout(){
                this.loggedin = false;
            },
            onClickChild: function(value) {
                this.loggedin = value;
                console.log(value) // someValue
            }
        }
    }
</script>

<style >
    .submitform {
        max-width: 300px;
        margin: auto;
    }
    .site-info {
        color: blue;
        margin-bottom: 20px;
    }

    .btn-primary {
        margin-right: 5px;
    }

    .container-fluid {
        text-align: center;
    }
</style>