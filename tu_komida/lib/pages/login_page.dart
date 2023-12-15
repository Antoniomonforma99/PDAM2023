// ignore_for_file: must_be_immutable
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tu_komida/blocs/login/login_bloc.dart';
import 'package:tu_komida/blocs/login/login_state.dart';

class LoginScreen extends StatelessWidget {
  LoginScreen({Key? key}) : super(key: key);

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  static Widget builder(BuildContext context) {
    return BlocProvider<LoginBloc>(
        create: (context) => LoginBloc(LoginState(loginModelObj: LoginModel()))
          ..add(LoginInitialEvent()),
        child: LoginScreen());
  }

  @override
  Widget build(BuildContext context) {
    mediaQueryData = MediaQuery.of(context);
    return SafeArea(
        child: Scaffold(
            resizeToAvoidBottomInset: false,
            appBar: _buildAppBar(context),
            body: Form(
                key: _formKey,
                child: Container(
                    width: double.maxFinite,
                    padding:
                        EdgeInsets.symmetric(horizontal: 18.h, vertical: 7.v),
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: [
                          SizedBox(height: 87.v),
                          Align(
                              alignment: Alignment.centerLeft,
                              child: Padding(
                                  padding: EdgeInsets.only(left: 8.h),
                                  child: Text("lbl_inicia_sesi_n".tr,
                                      style: theme.textTheme.displaySmall))),
                          SizedBox(height: 28.v),
                          _buildLoginComponentOne(context),
                          SizedBox(height: 34.v),
                          _buildLoginComponentTwo(context),
                          SizedBox(height: 32.v),
                          Text("msg_olvidaste_tu_contrase_a".tr,
                              style: CustomTextStyles.bodyLargePrimary17),
                          SizedBox(height: 20.v),
                          CustomElevatedButton(
                              text: "lbl_inicia_sesi_n2".tr,
                              margin: EdgeInsets.only(left: 47.h, right: 44.h)),
                          SizedBox(height: 33.v),
                          Align(
                              alignment: Alignment.centerLeft,
                              child: Padding(
                                  padding:
                                      EdgeInsets.only(left: 41.h, right: 51.h),
                                  child: Row(children: [
                                    Text("msg_no_tienes_una_cuenta".tr,
                                        style:
                                            CustomTextStyles.bodyLargeGray700),
                                    Text("lbl_registrate".tr,
                                        style:
                                            CustomTextStyles.bodyLargePrimary17)
                                  ]))),
                          SizedBox(height: 42.v),
                          _buildLoginComponentThree(context)
                        ]))),
            bottomNavigationBar: _buildGoogleButton(context)));
  }

  /// Section Widget
  PreferredSizeWidget _buildAppBar(BuildContext context) {
    return CustomAppBar(
        leadingWidth: double.maxFinite,
        leading: AppbarLeadingIconbutton(
            imagePath: ImageConstant.imgGroup17955,
            margin: EdgeInsets.fromLTRB(27.h, 21.v, 310.h, 24.v)),
        styleType: Style.bgFill);
  }

  /// Section Widget
  Widget _buildLoginComponentOne(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(left: 8.h, right: 4.h),
        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Padding(
              padding: EdgeInsets.only(left: 2.h),
              child: Text("lbl_e_mail".tr,
                  style: CustomTextStyles.titleMediumOnError)),
          SizedBox(height: 7.v),
          BlocSelector<LoginBloc, LoginState, TextEditingController?>(
              selector: (state) => state.emailController,
              builder: (context, emailController) {
                return CustomTextFormField(
                    controller: emailController,
                    hintText: "msg_antoniostudio_gmail_com".tr,
                    textInputType: TextInputType.emailAddress,
                    validator: (value) {
                      if (value == null ||
                          (!isValidEmail(value, isRequired: true))) {
                        return "err_msg_please_enter_valid_email".tr;
                      }
                      return null;
                    });
              })
        ]));
  }

  /// Section Widget
  Widget _buildLoginComponentTwo(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(left: 11.h, right: 4.h),
        child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
          Padding(
              padding: EdgeInsets.only(left: 2.h),
              child: Text("lbl_contrase_a".tr,
                  style: CustomTextStyles.titleMediumOnError)),
          SizedBox(height: 9.v),
          BlocBuilder<LoginBloc, LoginState>(builder: (context, state) {
            return CustomTextFormField(
                controller: state.passwordController,
                hintText: "lbl_contrase_a".tr,
                textInputAction: TextInputAction.done,
                textInputType: TextInputType.visiblePassword,
                suffix: InkWell(
                    onTap: () {
                      context.read<LoginBloc>().add(
                          ChangePasswordVisibilityEvent(
                              value: !state.isShowPassword));
                    },
                    child: Container(
                        margin: EdgeInsets.fromLTRB(30.h, 29.v, 30.h, 22.v),
                        child: CustomImageView(
                            imagePath: ImageConstant.imgEye,
                            height: 12.v,
                            width: 17.h))),
                suffixConstraints: BoxConstraints(maxHeight: 64.v),
                validator: (value) {
                  if (value == null ||
                      (!isValidPassword(value, isRequired: true))) {
                    return "err_msg_please_enter_valid_password".tr;
                  }
                  return null;
                },
                obscureText: state.isShowPassword,
                contentPadding:
                    EdgeInsets.only(left: 24.h, top: 22.v, bottom: 22.v));
          })
        ]));
  }

  /// Section Widget
  Widget _buildLoginComponentThree(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(right: 4.h),
        child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                  padding: EdgeInsets.only(top: 9.v, bottom: 10.v),
                  child: SizedBox(width: 85.h, child: Divider())),
              Text("msg_inicia_sesi_n_con".tr,
                  style: CustomTextStyles.titleMediumGray700),
              Padding(
                  padding: EdgeInsets.only(top: 9.v, bottom: 10.v),
                  child: SizedBox(width: 85.h, child: Divider()))
            ]));
  }

  /// Section Widget
  Widget _buildGoogleButton(BuildContext context) {
    return CustomElevatedButton(
        height: 57.v,
        width: 147.h,
        text: "lbl_google".tr,
        margin: EdgeInsets.only(left: 119.h, right: 109.h, bottom: 31.v),
        leftIcon: Container(
            margin: EdgeInsets.only(right: 14.h),
            child: CustomImageView(
                imagePath: ImageConstant.imgGoogle,
                height: 30.adaptSize,
                width: 30.adaptSize)),
        buttonStyle: CustomButtonStyles.outlineBlueGrayC,
        buttonTextStyle: theme.textTheme.bodyMedium!,
        onPressed: () {
          onTapGoogleButton(context);
        });
  }

  onTapGoogleButton(BuildContext context) async {
    await GoogleAuthHelper().googleSignInProcess().then((googleUser) {
      if (googleUser != null) {
        //TODO Actions to be performed after signin
      } else {
        ScaffoldMessenger.of(context)
            .showSnackBar(SnackBar(content: Text('user data is empty')));
      }
    }).catchError((onError) {
      ScaffoldMessenger.of(context)
          .showSnackBar(SnackBar(content: Text(onError.toString())));
    });
  }
}
