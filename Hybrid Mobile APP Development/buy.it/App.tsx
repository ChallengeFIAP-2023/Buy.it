import { StatusBar } from "react-native";
import { ThemeProvider } from "styled-components";
import { useFonts } from 'expo-font';
import { Roboto_400Regular, Roboto_700Bold } from "@expo-google-fonts/roboto";
import { Raleway_700Bold, Raleway_400Regular } from "@expo-google-fonts/raleway";

// Theme import
import theme from "@theme/index";

// Component import
import { Loading } from '@components/Loading';

// Screen import
import { Login } from "@screens/Login";

export default function App() {
  const [fontsLoaded] = useFonts({
    'Roboto-Regular': Roboto_400Regular,
    'Roboto-Bold': Roboto_700Bold,
    'Raleway-Bold': Raleway_700Bold,
    'Raleway-Regular': Raleway_400Regular,
  });

  return (
    <ThemeProvider theme={theme}>
      <StatusBar
        barStyle="light-content"
        backgroundColor="transparent"
        translucent
      />
      {fontsLoaded ? <Login /> : <Loading />}
    </ThemeProvider>
  )
}
