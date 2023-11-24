import { StatusBar } from "react-native";
import { ThemeProvider } from "styled-components";
import { useFonts } from 'expo-font';
import { Roboto_400Regular, Roboto_700Bold } from "@expo-google-fonts/roboto";
import { Raleway_700Bold, Raleway_400Regular, Raleway_600SemiBold } from "@expo-google-fonts/raleway";
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

// Theme import
import theme from "@theme/index";

// Component import
import { Loading } from '@components/Loading';

// Screen import
import { Login } from "@screens/Login";
import { Step1 } from "@screens/SignUp/Step1";
import { Step2 } from "@screens/SignUp/Step2";
import { Step3 } from "@screens/SignUp/Step3";

export default function App() {
  const [fontsLoaded] = useFonts({
    'Roboto-Regular': Roboto_400Regular,
    'Roboto-Bold': Roboto_700Bold,
    'Raleway-Regular': Raleway_400Regular,
    'Raleway-Semibold': Raleway_600SemiBold,
    'Raleway-Bold': Raleway_700Bold,
  });

  const Stack = createNativeStackNavigator();

  if (!fontsLoaded) {
    return <Loading />;
  }

  return (
    <ThemeProvider theme={theme}>
      <NavigationContainer>
        <StatusBar
          barStyle="light-content"
          backgroundColor="transparent"
          translucent
        />
        <Stack.Navigator initialRouteName="Login" screenOptions={{ headerShown: false }}>
          <Stack.Screen name="Login" component={Login} />
          <Stack.Screen name="Step1" component={Step1} />
          <Stack.Screen name="Step2" component={Step2} />
          <Stack.Screen name="Step3" component={Step3} />
        </Stack.Navigator>
      </NavigationContainer>
    </ThemeProvider>
  )
}
