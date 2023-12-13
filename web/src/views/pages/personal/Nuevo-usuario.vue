<script setup>
import { ref, onBeforeMount, onMounted } from 'vue';
import { useToast } from "primevue/usetoast";
import ProfileService from '../../../services/ProfileService';
import router from '../../../routes';

const name = ref('');
const email = ref('');
const tlf = ref('');
const contrasenya = ref('');
const repetirContrasenya = ref('');

const toast = useToast();

const profileService = new ProfileService();

async function createUser() {
console.log("click");

    let createUserRequestDTO = {
        name : name.value,
        email : email.value,
        password : contrasenya.value,
        verifyPassword : repetirContrasenya.value,
        tlf : tlf.value
    }

    console.log(createUserRequestDTO);

    await profileService.createUser(createUserRequestDTO)
    .then((resp) => {
            if (resp) {
                toast.add({
                severity : 'success',
                summary: 'Usuario creado con éxito',
                life : 3000
            })
        } else {
            toast.add({
                severity : 'error',
                summary: 'Ha ocurrido algún problema',
                life : 3000
            })
        }
        });
        setTimeout(() => {
            router.push('/personal')
        }, 1500);
}

</script>

<template>
    <Toast position="top-center" />

<div class="grid"> 
        <div class="xl:col-6 md:col-12 sm:col-12">
            <div class="card">
                <div style="display: flex; align-items: baseline; justify-content: space-between;">
    <h4 class="ml-3">Crear usuario</h4>
    <div style="display: flex;" class="ml-5">
        <Button @click="createUser()" label="Crear usuario" severity="danger" outlined class="mr-5 mb-5" />
    </div>
</div> 
            <Divider layout="horizontal">
                <b></b>
            </Divider>
            <div class="surface-section">
                <ul class="list-none p-0 m-0">
                    <li class="flex align-items-center py-3 px-4   flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Nombre</div>
                        <InputText class="pl-3 w-6 md:w-6" v-model="name"/>
                    </li>
                    <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Email</div>
                        <InputText class="pl-3 w-6 md:w-6" v-model="email"/>
                    </li>
                    <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Teléfono de notificación</div>
                        <InputText class="pl-3 w-6 md:w-6" v-model="tlf"/>
                    </li>
                    <li class="flex align-items-center py-3 px-4   flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Contraseña actual</div>
                        <!-- <InputText class="pl-3 w-6 md:w-6" v-model="contrasenyaAntigua"/> -->
                        <Password v-model="contrasenya" :feedback="false" toggleMask></Password>
                    </li>

                    <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                        <div class="ml-2 text-900 w-5 md:w-5 font-medium">Repetir nueva contraseña</div>
                        <!-- <InputText class="pl-3 w-6 md:w-6" v-model="repetirContrasenyaNueva"/> -->
                        <Password v-model="repetirContrasenya" :feedback="false" toggleMask></Password>
                    </li>

                
                </ul>
            </div>
            </div>

        </div>
</div>
        
</template>

<style lang="scss" scoped></style>