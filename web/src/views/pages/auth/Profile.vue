<script setup>   
import { ref, onBeforeMount, onMounted } from 'vue';
import { useToast } from "primevue/usetoast";
import ProfileService from '../../../services/ProfileService';


const toast = useToast();
const profile = ref({});


const name = ref('');
const tlf = ref('');
const username = ref('');
const email = ref('');
const contrasenyaAntigua = ref('');
const contrasenyaNueva = ref('');
const repetirContrasenyaNueva = ref('');


const profileService = new ProfileService();

async function getData(){
    await profileService.getData().then(
        (response) => {
            profile.value = response;
            
        }
    )
}

async function updatePassword() {

    console.log(contrasenyaAntigua.value)
    let changePasswordRequestDTO = {
        oldPassword : contrasenyaAntigua.value,
        newPassword : contrasenyaNueva.value,
        verifyNewPassword : repetirContrasenyaNueva.value
    }
        if (contrasenyaNueva.value === repetirContrasenyaNueva.value && contrasenyaNueva.value.length > 0) {
            await profileService.updatePassword(
                changePasswordRequestDTO
            ).then((resp) => {
                if (resp) {
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


onMounted(async () => {
    await getData();
    name.value = profile.value.name;
    username.value = profile.value.username;
    tlf.value = profile.value.tlf;
    email.value = profile.value.email;


});
</script>

<template>

<Toast position="top-center" />

    <div class="grid">
        <div class="col-12">
            <div class="card">
                <div style="display: flex; align-items: baseline; justify-content: space-between;">
    <h4 class="ml-3">Mi perfil</h4>
    <div style="display: flex;" class="ml-5">
        <Button @click="updatePassword()" label="Cambiar contraseña" severity="danger" outlined class="mr-5 mb-5" />
    </div>
</div> 


<div class="grid"> 
        <div class="xl:col-6 md:col-12 sm:col-12">
            <h5 class="ml-3">Datos de contacto</h5>
            <Divider layout="horizontal">
                <b></b>
            </Divider>
            <div class="surface-section">
        <ul class="list-none p-0 m-0">
            <li class="flex align-items-center py-3 px-4   flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Nombre</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="name"/>
            </li>
            <li class="flex align-items-center py-3 border-top-1 surface-border px-4   flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Username</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="username"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Teléfono de notificación</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="tlf"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Email</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="email"/>
            </li>

        </ul>
    </div>
        </div>
        <div class="xl:col-1 hidden lg:block">
            <Divider layout="vertical">
                <b></b>
            </Divider>
        </div>
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
            </div>
        </div>
    </div>


</template>

<style scoped lang="scss">
</style>