<script setup>
    
    import { ref, onBeforeMount, onMounted } from 'vue';
    import  Info from './Info.vue';  
    import Security from './Security.vue';

const tabs = ref([
{ title : 'Información personal', component : Info  },
{ title : 'Seguridad', component : Security} 
]);


</script>

<template>
    <div class="grid">
        <div class="xl:col-12">
            <div class="card xl:col-12">                   
                <TabView>
                    <TabPanel v-for="tab in tabs" :key="tab.title" :header="tab.title">
                        <component :is="tab.component"></component>                            
                    </TabPanel>
                </TabView> 
            </div>
        </div>
    </div>
</template>

<style lang="scss">
.border1{
    border-bottom-right-radius : 0;
    border-top-right-radius : 0;
}
.border2{
    border-bottom-left-radius : 0;
    border-top-left-radius : 0;
}
.botonGirado {
    transform: rotate(180deg);
    border-bottom-right-radius : 0;
    border-top-right-radius : 0;
}

</style>