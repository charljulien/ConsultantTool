<template>
    <div>
        <div>
            <h4>Project</h4>
            <div>
                <label>Name: </label> {{this.project.name}}
            </div>
            <div>
                <label>Description: </label> {{this.project.description}}
            </div>
            <div>
                <label>Deadline: </label> {{this.project.deadline}}
            </div>
            <div>
                <label>Active: </label> {{this.project.active}}
            </div>
        </div>
        <!--<button v-on:click="printProjectName()">printProjectName</button>-->
    </div>
    <!--<div v-if="this.customer">-->


        <!--<span v-if="this.customer.active"-->
              <!--v-on:click="updateActive(false)"-->
              <!--class="button is-small btn-primary">Inactive</span>-->
        <!--<span v-else-->
              <!--v-on:click="updateActive(true)"-->
              <!--class="button is-small btn-primary">Active</span>-->

        <!--<span class="button is-small btn-danger" v-on:click="deleteCustomer()">Delete</span>-->
    <!--</div>-->
    <!--<div v-else>-->
        <!--<br/>-->
        <!--<p>Please click on a Customer...</p>-->
    <!--</div>-->
</template>

<script>
    export default {
        name: "Project",
        props: ['project'],
        methods: {
            printProjectName(){
                console.log(this.project.name);
            }
        }
    }
</script>

<style>

</style>