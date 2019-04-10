<template>
    <div id="app" class="container-fluid">
        <p>login is set to {{ loggedin }}</p>
        <div v-if="!loggedin">
            <nav>
                <router-link class="btn btn-primary" to= "/menubar/login">Login</router-link>
                <router-link class="btn btn-primary" to="/menubar/register">Register</router-link>
            </nav>
        </div>
        <div v-else>
            <nav>
                <h4>Welcome {{ username }}</h4>
                <button class="btn btn-primary" v-on:click="logout()">Logout</button>
                <router-link class="btn btn-primary" to="/edit">Edit</router-link>
            </nav>
        </div>
        <br/>
        <router-view @isUserActive="initSession" @userSession="initUsername"></router-view>
    </div>
</template>

<script>
    export default {
        name: "menubar",
        data(){
            return{
                loggedin:false,
                username: ""
            }
        },
        // created() {
        //     if(this.$route.query.loggedin){
        //         this.loggedin = this.$route.query.loggedin;
        //     }
        // },
        methods: {
            logout(){
                this.loggedin = false;
                this.$emit('isUserActive', this.loggedin);
            },
            initSession: function(value) {
                this.loggedin = value;
                this.$emit('isUserActive', value);
                console.log(value) // someValue
            },
            initUsername: function(value){
                this.username = value;
                this.$emit('userSession', value);
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