// Component import
import {
  DecreasingContainer,
  StatusBar,
  Header,
  Highlight,
  Button,
} from "@components/index";

// Style import
import { Container, Pic, WrapPic, IconPic } from './styles';
import { ArrowRight, Image } from "phosphor-react-native";
import { useState } from "react";

import * as ImagePicker from 'expo-image-picker';

import theme from "@theme/index";

export function Step4({ navigation }: any) {

  const [logo, setLogo] = useState<string | null>(null);

  const pickImage = async () => {

    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [5,5],
      quality: 1,
      base64: true,
    });

    console.log(result);

    if (!result.canceled) {
      setLogo(result.assets[0].uri);
    }
  };

  return (
    <Container>
      {/* Arrumar esse dois componentes abaixo */}
      {/* <StatusBar /> */}
      {/* <Header /> */}

      <Highlight
        title="Logo da empresa"
        subtitle="Passo 4 de 5"
      />

      <DecreasingContainer>

        <WrapPic>
          {!logo ? (
            <Pic
              source={require('../../../assets/logo_default.png')}
            />
          ) : (
            <Pic
              source={{ uri: logo }}
            />
          )}
          <IconPic onPress={pickImage}>
            <Image size={theme.FONT_SIZE.XXL} color={theme.COLORS.WHITE} weight="bold" />
          </IconPic>
        </WrapPic>

        <Button 
          label="Escolher"
          onPress={pickImage}
          backgroundColor={theme.COLORS.GRAY_400}
        />
      </DecreasingContainer>

      <Button 
        label="Continuar"
        size="XL"
        icon={<ArrowRight color={'#fff'} weight="bold" />}
        bottom
        onPress={() => navigation.navigate("Step3")}
      />
    </Container>
  );
}
