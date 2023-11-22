import styled, { css } from "styled-components/native";

interface OptionProps {
    active?: boolean;
}

export const Container = styled.View`
  padding-top: 50px; // REMOVER ISSO (VOU ADICIONAR HEADER E STATUS BAR)

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const Content = styled.View`
    padding: 0 30px;
    flex: 1;
    justify-content: start;
    margin-top: 40px;
`;

export const Option = styled.Pressable<OptionProps>`
    background-color: ${({ theme }) => theme.COLORS.GRAY_600};
    border: solid 2px ${({ theme, active }) => active ? theme.COLORS.PRIMARY : theme.COLORS.GRAY_600};
    border-radius: 7px;
    padding: 20px 10px 23px;
    margin: 15px 0;
`;

export const OptionText = styled.Text<OptionProps>`
    color: ${({ theme, active }) => active ? theme.COLORS.PRIMARY_LIGHT : theme.COLORS.GRAY_200};
    font-family: ${({theme}) => theme.FONT_FAMILY.ROBOTO.BOLD}
    font-size: ${({theme}) => theme.FONT_SIZE.XL}px;
`