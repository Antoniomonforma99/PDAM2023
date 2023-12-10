<script setup>   
import { ref, onBeforeMount, onMounted } from 'vue';
import { useToast } from "primevue/usetoast";

const toast = useToast();
const profile = ref({});

const nombre = ref('');
const apellidos = ref('');
const nombreFiscal = ref('');
const nombreComercial = ref('');
const cif = ref('');
const iban = ref('');
const direccion = ref('');
const codigoPostal = ref('');
const city = ref('');
const tlf = ref('');
const emailNotificacion = ref('');
const emailFacturacion = ref('');

//const distributorService = new DistributorService();

async function updateProfile(){
    await distributorService.updateProfile(
        nombre.value, apellidos.value, nombreFiscal.value, nombreComercial.value,
        cif.value, iban.value, direccion.value, codigoPostal.value, city.value,
        tlf.value, emailNotificacion.value, emailFacturacion.value
    ).then((resp) => {
        if (resp === 1) {
            toast.add({
                severity : 'success',
                summary: 'Edición realizada con éxito',
                life : 3000
            })
        } else {
            toast.add({
                severity : 'error',
                summary: 'Ha ocurrido algún problema',
                life : 3000
            })
        }
    })
}



async function getProfile(){
    await distributorService.getProfile().then(
        (response) => {
            profile.value = response;
            console.log(profile.value);
        }
    )
}

onMounted(async () => {
    //await getProfile();
    nombre.value = profile.value.name;
    apellidos.value = profile.value.lastName;
    nombreFiscal.value = profile.value.nombre_fiscal;
    nombreComercial.value = profile.value.nombre_comercial;
    cif.value = profile.value.cif;
    iban.value = profile.value.IBAN;
    direccion.value = profile.value.address;
    codigoPostal.value = profile.value.cp;
    city.value = profile.value.city;
    tlf.value = profile.value.tlf_notificacion;
    emailNotificacion.value = profile.value.email;
    emailFacturacion.value = profile.value.email_facturacion;
    console.log(n)

});
</script>

<template>

<Toast position="top-center" />

<div style="display: flex; align-items: baseline; justify-content: space-between;">
    <h4 class="ml-3">Mi perfil</h4>
    <div style="display: flex;" class="ml-5">
        <Button @click="updateProfile()" label="Guardar cambios" outlined class="mr-5" />
    </div>
</div>


<!--<Divider layout="horizontal"><b></b></Divider>-->

<div style="margin-bottom: 3%;"></div>


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
                <InputText class="pl-3 w-6 md:w-6" v-model="nombre"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Apellidos</div>
                <InputText class="pl-3 w- md:w-6" v-model="apellidos"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Teléfono de notificación</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="tlf"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Email de notificación</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="emailNotificacion"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Email de facturación</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="emailFacturacion"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Dirección</div>
                <InputText class="border1 pl-3 w-6 md:w-6" v-model="direccion"/>
                
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Ciudad</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="city"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Código postal</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="codigoPostal"/>
            </li>
        </ul>
    </div>
        </div>
        <div class="xl:col-1 hidden lg:block">
            <Divider layout="vertical">
                <b></b>
            </Divider>
        </div>
        <div class="xl:col-5 md:col-12 sm:col-12">
            <h5 class="ml-3">Datos comerciales</h5>
            <Divider layout="horizontal">
                <b></b>
            </Divider>
            <div class="surface-section">
        <ul class="list-none p-0 m-0">
            <li class="flex align-items-center py-3 px-4 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Nombre comercial</div>
                <p class="pl-3">{{ profile.nombre_comercial }}</p>  
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Nombre fiscal</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="nombreFiscal"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">CIF</div>
                <InputText class="pl-3 w- md:w-6" v-model="cif"/>
            </li>
            <li class="flex align-items-center py-3 px-4 border-top-1 surface-border flex-wrap">
                <div class="ml-2 text-900 w-5 md:w-5 font-medium">Número de cuenta (IBAN)</div>
                <InputText class="pl-3 w-6 md:w-6" v-model="iban"/>
            </li>
        </ul>
    </div>
</div>
</div>
</template>

<style scoped lang="scsss">
</style>