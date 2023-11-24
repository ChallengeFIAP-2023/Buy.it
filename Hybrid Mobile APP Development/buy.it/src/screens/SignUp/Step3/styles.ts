import styled from "styled-components/native";

export const Container = styled.View`
  padding-top: 50px; // REMOVER ISSO (VOU ADICIONAR HEADER E STATUS BAR)

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const Fieldset = styled.View`
  width: 100%;
  margin-bottom: 25px;
`;

export const WrapDropdown = styled.View`
  margin: 0;
`;

export const Subtitle = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
  margin-top: 25px;
`;

export const WrapContacts = styled.FlatList`
  margin-top: 15px;
  margin-bottom: 25px;
`;