import { ThemeProvider } from 'styled-components';
import { useFonts } from 'expo-font';
import { Roboto_400Regular, Roboto_700Bold } from '@expo-google-fonts/roboto';
import {
  Raleway_700Bold,
  Raleway_400Regular,
  Raleway_600SemiBold,
} from '@expo-google-fonts/raleway';
import Toast from 'react-native-toast-message';
import { NavigationContainer } from '@react-navigation/native';

// Route import
import Routes from '@routes/index';

// Theme import
import theme from '@theme/index';

// Component import
import { Loading } from '@components/Loading';

export default function App() {
  const [fontsLoaded] = useFonts({
    'Roboto-Regular': Roboto_400Regular,
    'Roboto-Bold': Roboto_700Bold,
    'Raleway-Regular': Raleway_400Regular,
    'Raleway-Semibold': Raleway_600SemiBold,
    'Raleway-Bold': Raleway_700Bold,
  });

  return (
    <NavigationContainer>
      <ThemeProvider theme={theme}>
        {!fontsLoaded ? <Loading /> : <Routes />}

        <Toast />
      </ThemeProvider>
    </NavigationContainer>
  );
}
