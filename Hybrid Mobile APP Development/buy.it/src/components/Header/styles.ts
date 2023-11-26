import { ArrowLeft } from 'phosphor-react-native';
import styled from 'styled-components/native';

// Asset import
import LogoImage from "@assets/logo.png"

export const Container = styled.View`
  width: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};

  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 0 30px;
`;

export const Logo = styled.Image.attrs({
  source: LogoImage
})`
  width: 40px;
  height: 45px;
`;

export const BackButton = styled.TouchableOpacity`
  flex: 1;
`;

export const BackIcon = styled(ArrowLeft).attrs(({ theme }) => ({
  size: theme.FONT_SIZE.XXL,
  color: theme.COLORS.GRAY_300
}))``;
