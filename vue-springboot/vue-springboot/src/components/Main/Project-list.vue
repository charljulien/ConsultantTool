<template>
    <div class="row list">
        <div v-if="login">
            <div class="col-md-6">
                <h4>Project I created</h4>
                <ul>
                    <li v-for="(project, index) in projects" :key="index">
                        <router-link :to="{
                            name: 'project',
                            params: { project: project, id: project.id }
                        }">
                            {{project.name}}
                        </router-link>
                    </li>
                    <button class="btn btn-primary">Create a new project ?</button>
                </ul>
                <h4>Project shared with me</h4>
            </div>
            <div class="col-md-6">
                <!--<app-project @refreshData="refreshList"></app-project>-->
                <router-view @refreshData="refreshList"></router-view>
            </div>
        </div>
    </div>
</template>

<script>
    import http from "../../http-common";
    import Project from '../Main/Project';

    export default {
        components: {
            'app-project': Project
        },
        props: ['username', 'login'],
        name: "project-list",
        data() {
            return {
                projects: [],
                user: {}
            };
        },
        methods: {
            /* eslint-disable no-console */
            retrieveProjects() {
                http
                    .get("/user/" + this.username)
                    .then(response => {
                        this.user = response.data; // JSON are parsed automatically.
                        console.log(this.user);
                        var i;
                        for(i = 0; this.user.createdProjects.length; i++){
                            console.log(this.user.createdProjects[i].name);
                            this.projects.push(this.user.createdProjects[i]);
                        }
                        for(i = 0; this.projects.length; i++){
                            console.log("Projects dans la liste Projects " + this.projects.name);
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            refreshList() {
                this.retrieveProjects();
            },
            print() {
                console.log(this.username);
                this.retrieveProjects();
            }
            /* eslint-enable no-console */
        },
        mounted() {
            this.retrieveProjects();
        }
    };
</script>

<style>
    .list{
        text-align: left;
        /*max-width: 450px;*/
        margin: auto;
        width: 100%;
    }
    .col-md-6{
        float: right;
        text-align: center;
        width: 50%;
        padding: 10px;
        height: 300px; /* Should be removed. Only for demonstration */
        background-color:#c0c0c0;
    }
    /* Create two equal columns that floats next to each other */
    .column {
        float: left;
        width: 50%;
        padding: 10px;
        height: 300px; /* Should be removed. Only for demonstration */
        background-color:#aaa;
    }

    /* Clear floats after the columns */
    .row:after {
        content: "";
        display: table;
        clear: both;
    }
</style>
