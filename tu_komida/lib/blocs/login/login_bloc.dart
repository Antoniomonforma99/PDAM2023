import 'package:bloc/bloc.dart';
import 'package:tu_komida/blocs/authentication/auth_bloc.dart';
import 'login_event.dart';
import 'login_state.dart';


class LoginBloc extends Bloc<LoginEvent, LoginState> {
  final AuthenticationBloc _authenticationBloc;
  final AuthenticationService _authenticationService;

  LoginBloc(AuthenticationBloc authenticationBloc, AuthenticationService authenticationService)
      : assert(authenticationBloc != null),
        assert(authenticationService != null),
        _authenticationBloc = authenticationBloc,
        _authenticationService = authenticationService,
        super(LoginInitial()) {
          on<LoginInWithEmailButtonPressed>(__onLogingInWithEmailButtonPressed);
        }


  __onLogingInWithEmailButtonPressed(
    LoginInWithEmailButtonPressed event,
    Emitter<LoginState> emit,
  ) async {
    emit(LoginLoading());
    try {
      final user = await _authenticationService.signInWithEmailAndPassword(event.email, event.password);
      if (user != null) {
        _authenticationBloc.add(UserLoggedIn(user: user));
        emit(LoginSuccess());
      } else {
        emit(LoginFailure(error: 'Something very weird just happened'));
      }
    } on AuthenticationException catch (e) {
      emit(LoginFailure(error: e.message));
    } on CustomException catch (err) {
      emit(LoginFailure(error:'An unknown error occurred ${err.message}'));
    }
  }

  
}