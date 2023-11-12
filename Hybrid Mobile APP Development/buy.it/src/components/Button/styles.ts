import styled from 'styled-components/native';

export const ButtonContainer = styled.TouchableOpacity`
  background-color: ${({ theme }) => theme.COLORS.PRIMARY};

  border-radius: 8px;
  padding: 16px;

  width: 100%;
`;

export const ButtonText = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  text-align: center;
`;
