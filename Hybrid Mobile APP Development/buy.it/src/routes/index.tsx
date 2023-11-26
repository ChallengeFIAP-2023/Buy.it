import { StatusBar } from "react-native";
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

// Screen import
import { Login } from "@screens/Login";
import { Step1 } from "@screens/SignUp/Step1";
import { Step2 } from "@screens/SignUp/Step2";
import { Step3 } from "@screens/SignUp/Step3";
import { Step4 } from "@screens/SignUp/Step4";
import { Step5 } from "@screens/SignUp/Step5";

export default function Routes() {

  const Stack = createNativeStackNavigator();

  return (
    <>
      <StatusBar
        barStyle="light-content"
        backgroundColor="transparent"
        translucent
      />
      {/* TODO: verificar se o usuário está logado para mudar a rota inicial */}
      <Stack.Navigator initialRouteName="Login" screenOptions={{ headerShown: false }}>
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="Step1" component={Step1} />
        <Stack.Screen name="Step2" component={Step2} />
        <Stack.Screen name="Step3" component={Step3} />
        <Stack.Screen name="Step4" component={Step4} />
        <Stack.Screen name="Step5" component={Step5} />
      </Stack.Navigator>
    </>
  )
}
