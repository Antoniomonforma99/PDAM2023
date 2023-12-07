<script setup>
import { useLayout } from '@/layout/composables/layout';
import { ref, computed, onBeforeMount } from 'vue';
import AppConfig from '@/layout/AppConfig.vue';
import router from '@/routes/index.js';
import useAuthStore from '@/stores/authStore.js';
import { useToast } from "primevue/usetoast";
import { onMounted } from 'vue';

const { layoutConfig } = useLayout();
const authStore = useAuthStore();
const toast = useToast();

const email = ref('');
const password = ref('');
const rememberMeChecked = ref(false);
const loading = ref(false);

let errorMessageEmail = "";
let errorMessagePassword = "";

const logoUrl = computed(() => {
    if (layoutConfig.darkTheme.value) {
        return '/admin/layout/images/'
    } else {
        return '/admin/layout/images'
    }
});


onMounted(() => {
    if (localStorage.getItem('sessionExpiredAlert') == 1) {
        toast.add({
            severity: 'error',
            summary: 'Caducó la sesión de usuario',
            detail: 'Inicie sesión de nuevo',
            life: 3000
        });

        localStorage.removeItem('sessionExpiredAlert');
    }
});


async function doLogin() {
    loading.value = true

    if (checkFields()) {
        await authStore.login(email.value, password.value, rememberMeChecked.value).then((response) => {
            if (response === 1) {
                router.push('/dashboard')
            } else if (response === -1) {
                loading.value = false
                toast.add({
                    severity: 'error',
                    summary: 'Credenciales incorrectas',
                    detail: 'Inténtelo de nuevo',
                    life: 3000
                });
            }
        });
    }
}

function checkFields() {
    if (email.value !== "" && password.value !== "") {
        return true;
    } else {
        if (email.value === "") {
            errorMessageEmail = "Rellene el email";
        } else {
            errorMessageEmail = "";
        }

        if (password.value === "") {
            errorMessagePassword = "Rellene la contraseña";
        } else {
            errorMessagePassword = "";
        }

        loading.value = false;
        return false;
    }
}

</script>

<template>
    <div class="surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden">

        <Toast position="top-center" />

        <div class="flex flex-column align-items-center justify-content-center">
            <div style="border-radius: 56px; padding: 0.3rem;">
                <div class="w-full surface-card py-8 px-5 sm:px-8" style="border-radius: 53px">
                    <div class="text-center mb-5 logo-text">
                        <img :src="logoUrl" alt="TuKomida logo" class="mb-2 w-10rem flex-shrink-0" />
                    </div>

                    <div>
                        <span style="display: block;" class="mb-4">
                            <label for="email" class="block text-900 text-xl font-medium mb-2">Email</label>
                            <InputText id="email" type="text" placeholder="Correo electrónico" class="w-full md:w-30rem"
                                style="padding: 1rem" v-model="email" :class="{ 'p-invalid': errorMessageEmail }" />
                            <span style="display: block;" class="mt-1">
                                <small class="p-error" id="text-error">{{ errorMessageEmail || '&nbsp;' }}</small>
                            </span>
                        </span>

                        <span style="display: block;" class="mb-5">
                            <label for="password" class="block text-900 text-xl font-medium mb-2">Contraseña</label>

                            <Password id="password" v-model="password" placeholder="Contraseña" :toggleMask="true"
                                :feedback="false" class="w-full" inputClass="w-full" :inputStyle="{ padding: '1rem' }">
                            </Password>

                            <span style="display: block;" class="mt-1">
                                <small class="p-error" id="text-error">{{ errorMessagePassword || '&nbsp;' }}</small>
                            </span>
                        </span>

                        <div class="flex align-items-center justify-content-between mb-5 gap-5">
                            <div class="flex align-items-center">
                                <Checkbox v-model="rememberMeChecked" id="rememberMeChecked" binary class="mr-2"></Checkbox>
                                <label for="rememberMeChecked">Recuérdame</label>
                            </div>
                            <a class="font-medium no-underline ml-2 text-right cursor-pointer"
                                style="color: var(--primary-color)">He olvidado mi contraseña</a>
                        </div>
                        <Button :loading="loading" @click="doLogin" label="Entrar" class="w-full p-3 text-xl"></Button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <AppConfig simple />
</template>

<style scoped>
.pi-eye {
    transform: scale(1.6);
    margin-right: 1rem;
}

.pi-eye-slash {
    transform: scale(1.6);
    margin-right: 1rem;
}

.logo-text {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.logo-text span {
    margin-top: 0.2rem;
    font-size: 1.2rem;
}

@media (max-width: 991px) {
    .logo-text span {
        font-size: 1rem;
    }

}
</style>