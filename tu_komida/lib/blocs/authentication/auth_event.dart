import '../../model/models.dart';
import 'package:equatable/equatable.dart';

abstract class AuthenticationEvent extends Equatable {
  const AuthenticationEvent();

  @override
  List<Object> get props => [];
}


class AppLoaded extends AuthenticationEvent {}


class UserLoggedIn extends AuthenticationEvent {
  final User user;

  UserLoggedIn({required this.user});

  @override
  List<Object> get props => [user];
}

// Fired when the user has logged out
class UserLoggedOut extends AuthenticationEvent {}


// Se emite cuando la sesión ha expirado
class SessionExpiredEvent extends AuthenticationEvent {}