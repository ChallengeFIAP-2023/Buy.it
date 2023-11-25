import styled from "styled-components/native";

export const Container = styled.View`
  padding-top: 50px; // REMOVER ISSO (VOU ADICIONAR HEADER E STATUS BAR)

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const WrapPic = styled.View`
  margin: 30px 0;
  width: 100%;
  height: auto;
  border-radius: 80px;
  align-items: center;
  justify-content: center;
`;

export const Pic = styled.Image`
  background-color: ${({theme}) => theme.COLORS.GRAY_400};
  width: 200px;
  height: 200px;
  border-radius: 125px;
  border-width: 8px;
  border-color: ${({theme}) => theme.COLORS.GRAY_300};
`;

export const IconPic = styled.TouchableOpacity`
  background-color: ${({ theme }) => theme.COLORS.PRIMARY };
  position: absolute;
  border-radius: 35px;
  width: 60px;
  height: 60px;
  bottom: 10px;
  right: 50px;
  align-items: center;
  justify-content: center;
`;