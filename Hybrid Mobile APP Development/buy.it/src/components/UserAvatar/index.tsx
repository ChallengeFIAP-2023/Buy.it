import React from 'react';
import { Image as ImageIcon } from "phosphor-react-native";
import { ImageSourcePropType } from 'react-native';
import * as ImagePicker from 'expo-image-picker';

// Theme import
import theme from "@theme/index";

// Style import
import { Container, Image, IconPic } from './styles';

interface Props {
  handleSetAvatar: (uri: string) => void;
  imageSource: ImageSourcePropType;
  size: "MD" | "XLL";
}

export function UserAvatar({
  handleSetAvatar,
  imageSource,
  size = "XLL"
}: Props) {
  const iconSize = size === "XLL" ? theme.FONT_SIZE.XXL : theme.FONT_SIZE.MD;

  async function handleSelectImage() {
    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [5, 5],
      quality: 1,
      base64: true,
    });

    if (!result.canceled) handleSetAvatar(result.assets[0].uri);
  };

  return (
    <Container onPress={handleSelectImage} activeOpacity={0.7}>
      <Image source={imageSource} size={size} />

      <IconPic size={size}>
        <ImageIcon
          size={iconSize}
          color={theme.COLORS.WHITE}
          weight="bold"
        />
      </IconPic>
    </Container>
  );
}
