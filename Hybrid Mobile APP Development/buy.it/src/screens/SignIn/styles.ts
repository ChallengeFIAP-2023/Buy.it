import styled from "styled-components/native";

export const Container = styled.View`
  padding-top: 20px;

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const Fieldset = styled.View`
  width: 100%;

  margin-bottom: 45px;
`;

export const Touchable = styled.TouchableOpacity`
  align-items: center;
  flex-direction: row;
  gap: 5px;
  justify-content: center;

  margin-top: 45px;
`;

export const RegisterText = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.REGULAR};
`;

export const RegisterTextBold = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
`;
