import 'package:json_annotation/json_annotation.dart';
class RegisterUserRequest {

  late String username;
  late String password;
  late String verifyPassword;
  late String email;
  late String name;
  late String surname;
  late String birthday;
  
  RegisterUserRequest({required this.username, required this.password, required this.verifyPassword, 
  required this.name, required this.surname });

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['username'] = username;
    data['password'] = password;
    data['verifyPassword'] = verifyPassword;
    data['name'] = name;
    data['surname'] = surname;

    return data;
  }


}