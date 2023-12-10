<script setup>
    
    import { ref, onBeforeMount, onMounted } from 'vue';
    import { useToast } from "primevue/usetoast";  

    const toast = useToast();


    // const distributorService = new DistributorService();

    const contrasenyaAntigua = ref('');
    const contrasenyaNueva = ref('');
    const repetirContrasenyaNueva = ref('');

    async function updatePassword() {
        if (contrasenyaNueva.value === repetirContrasenyaNueva.value && contrasenyaNueva.value.length > 0) {
            await distributorService.updatePassword(
                contrasenyaAntigua.value, contrasenyaNueva.value, repetirContrasenyaNueva.value
            ).then((resp) => {
                if (resp === 1) {
                    toast.add({
                        severity : 'success',
                        summary: 'Contraseña actualizada con éxito',
                        life : 3000
                    })
                } else {
                    toast.add({
                        severity : 'error',
                        summary: 'La contraseña actual no es correcta',
                        life : 3000
                    })
                }
            })

        } else {
            toast.add({
                severity : 'error',
                summary: 'Las contraseñas no coinciden',
                life : 3000
            })
        }
        
    }
</script>

<template>
    <div style="display: flex; align-items: baseline; justify-content: space-between;">
    <h4 class="ml-3">Seguridad</h4>
    <div style="display: flex;" class="ml-5">
        <Button @click="updatePassword()" label="Cambiar contraseña" severity="danger" outlined class="mr-5 mb-5" />
    </div>
    </div>
    <div class="grid"> 
        <div class="xl:col-6 lg:col-12 md:col-12 sm:col-12">
            <!-- <h5 class="text-center">Actualizar contraseña</h5>
            <Divider layout="horizontal">
                <b></b>
            </Divider> -->
            <div class="surface-section">                               
                <ul class="list-none p-0 m-0">
                    <li class="flex align-items-center py-3 px-4   flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Contraseña actual</div>
                        <!-- <InputText class="pl-3 w-6 md:w-6" v-model="contrasenyaAntigua"/> -->
                        <Password v-model="contrasenyaAntigua" :feedback="false" toggleMask></Password>
                    </li>
                    <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Nueva contraseña</div>
                        <!-- <InputText class="pl-3 w- md:w-6" v-model="contrasenyaNueva"/> -->
                        <Password v-model="contrasenyaNueva" :feedback="false" toggleMask></Password>
                    </li>
                    <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Repetir nueva contraseña</div>
                        <!-- <InputText class="pl-3 w-6 md:w-6" v-model="repetirContrasenyaNueva"/> -->
                        <Password v-model="repetirContrasenyaNueva" :feedback="false" toggleMask></Password>
                    </li>
                </ul>
            </div>
        </div>
        <div class="xl:col-1 hidden lg:block">
            <!-- <Divider layout="vertical">
                <b></b>
            </Divider> -->
        </div>
        <div class="xl:col-5 md:col-12 sm:col-12">
            <h5 class="text-center"></h5>
            <!-- <Divider layout="horizontal">
                <b></b>
            </Divider> -->
            <div class="surface-section">
        <ul class="list-none p-0 m-0">
            <li class="flex align-items-center py-3 px-4 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium"></div>
                <p class="pl-3"></p>  
            </li>

        </ul>
    </div>
</div>
    </div>
</template>

<style scoped lang="scss"></style>